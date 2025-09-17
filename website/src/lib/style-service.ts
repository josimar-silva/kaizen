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
};

export default StyleService;
