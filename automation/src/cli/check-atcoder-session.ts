import "dotenv/config";
import { AtCoderProblemsClient } from "../adapters/atcoder/atcoder-problems-client.js";
import { AtCoderSubmissionClient } from "../adapters/atcoder/submission-page.js";

const account = process.env.ATCODER_ACCOUNT ?? "sprout6626";
const session = process.env.ATCODER_REVEL_SESSION;

if (!session) {
  console.error("ATCODER_REVEL_SESSION is required. Do not commit or print this value.");
  process.exitCode = 2;
} else {
  const metadataClient = new AtCoderProblemsClient();
  const submissions = await metadataClient.fetchUserSubmissions(account, 0);
  const latestAccepted = submissions.filter((item) => item.result === "AC").at(-1);
  if (!latestAccepted) throw new Error(`No accepted submission found for ${account}`);

  const detail = await new AtCoderSubmissionClient(session).fetchSubmission(
    latestAccepted.contestId,
    latestAccepted.id
  );

  console.log(JSON.stringify({
    authenticated: true,
    account,
    submissionId: latestAccepted.id,
    problemId: latestAccepted.problemId,
    sourceLength: detail.sourceCode.length,
    ...(detail.memory ? { memory: detail.memory } : {})
  }));
}
