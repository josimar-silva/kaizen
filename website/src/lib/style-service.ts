import { SourceName } from "@/interfaces/algorithm";

const StyleService = {
  getLanguageIcon: (language: string): string => {
    switch (language) {
      case "Rust":
        return "devicon-rust-plain colored";
      case "Python":
        return "devicon-python-plain colored";
      case "Java":
        return "devicon-java-plain colored";
      case "Go":
        return "devicon-go-plain colored";
      case "C++":
        return "devicon-cplusplus-plain colored";
      case "TypeScript":
        return "devicon-typescript-plain colored";
      case "Kotlin":
        return "devicon-kotlin-plain colored";
      default:
        return "devicon-devicon-plain";
    }
  },
  getLanguageColor: (language: string): string => {
    switch (language) {
      case "Rust":
        return "#DEA584";
      case "Python":
        return "#3776AB";
      case "Java":
        return "#973013ff";
      case "Go":
        return "#00ADD8";
      case "C++":
        return "#00599C";
      case "TypeScript":
        return "#3178C6";
      case "Kotlin":
        return "#7F52FF";
      default:
        return "#808080";
    }
  },
  getSolutionTypeColor: (type: string): string => {
    switch (type) {
      case "algorithm":
        return "#8522eeff";
      case "system-design":
        return "#FFBB28";
      default:
        return "#808080";
    }
  },
  getSourceColor: (source: SourceName | string): string => {
    switch (source) {
      case SourceName.LeetCode:
        return "#FFA116";
      case SourceName.ProjectEuler:
        return "#11c1b6ff";
      case SourceName.Coursera:
        return "#0056D2";
      case SourceName.Codewars:
        return "#B1361E";
      default:
        return "#808080";
    }
  },
  getHumanReadableSourceName: (source: SourceName | string): string => {
    switch (source) {
      case SourceName.LeetCode:
        return "LeetCode";
      case SourceName.ProjectEuler:
        return "Project Euler";
      case SourceName.Coursera:
        return "Coursera";
      case SourceName.Codewars:
        return "Codewars";
      default:
        return source;
    }
  },
};

export default StyleService;
