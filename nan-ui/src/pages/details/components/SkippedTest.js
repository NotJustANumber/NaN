import React from "react";
import { SkippedIcon } from "../../../components/Icons";

function SkippedTest(props) {
  return (
    <div className="test-child px-8 text-gray-700 py-2 flex items-center mx-auto">
      <SkippedIcon className="mr-3"></SkippedIcon>
      <span className="truncate">{props.result.name}</span>
    </div>
  );
}

export default SkippedTest;
