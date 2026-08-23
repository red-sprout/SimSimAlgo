const SUBMISSION_CODE_PATTERN = /<pre\b[^>]*\bid=["']submission-code["'][^>]*>([\s\S]*?)<\/pre>/i;
const MEMORY_PATTERN = /<th[^>]*>\s*Memory\s*<\/th>\s*<td[^>]*>\s*([^<]+?)\s*<\/td>/i;

const NAMED_ENTITIES: Readonly<Record<string, string>> = {
  amp: "&",
  apos: "'",
  gt: ">",
  lt: "<",
  nbsp: "\u00a0",
  quot: "\""
};

export interface AtCoderSubmissionPage {
  sourceCode: string;
  memory?: string;
}

export class AtCoderAuthenticationError extends Error {
  constructor(message = "AtCoder authentication is missing or expired") {
    super(message);
    this.name = "AtCoderAuthenticationError";
  }
}

function decodeHtmlEntities(value: string): string {
  return value.replace(/&(#(?:x[0-9a-f]+|\d+)|[a-z]+);/gi, (entity, body: string) => {
    if (body.startsWith("#x") || body.startsWith("#X")) {
      return String.fromCodePoint(Number.parseInt(body.slice(2), 16));
    }
    if (body.startsWith("#")) return String.fromCodePoint(Number.parseInt(body.slice(1), 10));
    return NAMED_ENTITIES[body.toLowerCase()] ?? entity;
  });
}

export function parseSubmissionPage(html: string): AtCoderSubmissionPage {
  const code = SUBMISSION_CODE_PATTERN.exec(html)?.[1];
  if (code === undefined) {
    if (/\/login(?:\?|["'])|name=["']username["']/i.test(html)) throw new AtCoderAuthenticationError();
    throw new Error("AtCoder submission page does not contain #submission-code");
  }

  const memory = MEMORY_PATTERN.exec(html)?.[1];
  return {
    sourceCode: decodeHtmlEntities(code.replace(/\r\n/g, "\n")),
    ...(memory ? { memory: decodeHtmlEntities(memory.trim()) } : {})
  };
}

export class AtCoderSubmissionClient {
  constructor(
    private readonly revelSession: string,
    private readonly fetchImpl: typeof fetch = fetch,
    private readonly baseUrl = "https://atcoder.jp"
  ) {
    if (!revelSession.trim()) throw new AtCoderAuthenticationError("ATCODER_REVEL_SESSION is empty");
    if (/\r|\n|;/.test(revelSession)) throw new Error("Invalid ATCODER_REVEL_SESSION value");
  }

  async fetchSubmission(contestId: string, submissionId: string): Promise<AtCoderSubmissionPage> {
    const url = new URL(`/contests/${encodeURIComponent(contestId)}/submissions/${encodeURIComponent(submissionId)}`, this.baseUrl);
    url.searchParams.set("lang", "en");
    const response = await this.fetchImpl(url, {
      headers: {
        accept: "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
        "accept-language": "en-US,en;q=0.9",
        cookie: `REVEL_SESSION=${this.revelSession}`,
        "sec-fetch-dest": "document",
        "sec-fetch-mode": "navigate",
        "sec-fetch-site": "none",
        "upgrade-insecure-requests": "1",
        "user-agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36"
      },
      redirect: "follow",
      signal: AbortSignal.timeout(20_000)
    });

    if (response.status === 401 || response.status === 403 || response.url.includes("/login")) {
      throw new AtCoderAuthenticationError(`AtCoder rejected the session with HTTP ${response.status}`);
    }
    if (!response.ok) throw new Error(`AtCoder submission request failed: HTTP ${response.status}`);
    return parseSubmissionPage(await response.text());
  }
}
