import React from "react";
import { useHistory } from "react-router-dom";

const brandStyle = {
  fontSize: "24px",
  letterSpacing: "2px"
};

function Header() {
  let history = useHistory();
  return (
    <header className="bg-indigo-600 sm:flex sm:justify-between sm:items-center sm:py-1">
      <div
        className="container mx-auto flex cursor-pointer items-center justify-between mx-3 py-3 sm:p-0"
        onClick={() => history.push("/")}
      >
        <div className="flex flex-col">
          <span className="text-indigo-100 font-medium" style={brandStyle}>
            NaN
          </span>
          <span className="text-indigo-100 text-sm tracking-tight">Not Just A Number</span>
        </div>
      </div>
    </header>
  );
}

export default Header;
