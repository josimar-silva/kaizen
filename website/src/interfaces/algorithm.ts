export interface Algorithm {
  title: string;
  notes: string;
  link: string;
  language: string;
  type: "algorithm" | "system-design";
  reference?: string;
  analysis?: string;
  source?: string;
}

export enum SourceName {
  // eslint-disable-next-line no-unused-vars
  LeetCode = "leetcode",
  // eslint-disable-next-line no-unused-vars
  ProjectEuler = "project-euler",
  // eslint-disable-next-line no-unused-vars
  Coursera = "coursera",
  // eslint-disable-next-line no-unused-vars
  Codewars = "codewars",
}
