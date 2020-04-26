import React, { useState } from "react";
import { FailIcon } from "../../../components/Icons";

function FailedTest(props) {
  const result = props.result;
  const [isShown, makeVisible] = useState(false);

  function setVisible(e) {
    e.preventDefault();
    makeVisible(!isShown);
  }

  return (
    <div className="test-child cursor-pointer px-8 my-2 py-1 text-gray-700 flex flex-col items-start mx-auto">
      <div className="result" onClick={setVisible}>
        <FailIcon className="mr-3"></FailIcon>
        <span className="w-9/12 overflow-hidden">{result.name}</span>
      </div>

      <div className="duration-area mt-1 text-sm text-gray-500 flex items-center">
        ran for
        {"  " + result.duration}s
      </div>

      {isShown ? (
        <div className="error w-full text-sm p-3 text-gray-500">
          {result.throwable}
        </div>
      ) : null}
    </div>
  );
}

export default FailedTest;
