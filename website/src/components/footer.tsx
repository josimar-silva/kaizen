export function Footer() {
  const version = process.env.APP_VERSION;

  return (
    <footer className="border-t border-border bg-card/30 mt-12 sm:mt-16">
      <div className="container mx-auto px-4 sm:px-6 py-6 sm:py-8 text-center">
        <div className="space-y-2">
          <p className="text-xs sm:text-sm text-muted-foreground">
            Built with viele ☕️ by{" "}
            <a
              href="https://josimar-silva.com"
              target="_blank"
              rel="noopener noreferrer"
              className="text-purple-400 font-medium hover:text-purple-300 transition-colors duration-200 underline decoration-purple-400/50 hover:decoration-purple-300"
              data-testid="footer-author-link"
            >
              Josimar Silva
            </a>
          </p>
          <p data-testid="footer-copyright" className="text-xs text-slate-500">
            &copy; {new Date().getFullYear()} Josimar Silva. All rights
            reserved.
            {version && ` v${version}`}
          </p>
        </div>
      </div>
    </footer>
  );
}
