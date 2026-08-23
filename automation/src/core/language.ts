const EXTENSIONS: ReadonlyArray<readonly [RegExp, string]> = [
  [/c\+\+/i, "cpp"],
  [/^c(?:\s|$|\d)/i, "c"],
  [/java/i, "java"],
  [/python|pypy/i, "py"],
  [/kotlin/i, "kt"],
  [/rust/i, "rs"],
  [/go(?:\s|$)/i, "go"],
  [/javascript|node\.js/i, "js"],
  [/typescript|deno/i, "ts"],
  [/swift/i, "swift"],
  [/ruby/i, "rb"],
  [/c#/i, "cs"],
  [/sql/i, "sql"]
];

export function extensionForLanguage(language: string): string {
  return EXTENSIONS.find(([pattern]) => pattern.test(language))?.[1] ?? "txt";
}
