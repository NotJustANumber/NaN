module.exports = {
  purge: {
    enabled: true,
    content: ["./src/**/*.html", "./src/**/*.js"],
  },
  theme: {
    fontFamily: {
      sans: ["Inter"],
    },
    extend: {
      gridTemplateColumns: {
        // Simple 16 column grid
        "24": "repeat(24, minmax(0, 1fr))",
      },
    },
  },
  variants: {},
  plugins: [],
};
