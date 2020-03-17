import React from "react";
import { DurationIcon, FailIcon, SkippedIcon } from "../../../components/Icons";
import PassedTest from "./PassedTest";
import FailedTest from "./FailedTest";
import SkippedTest from "./SkippedTest";

const success = "SUCCESSFUL";
const failed = "FAILED";
const skipped = "SKIPPED";

function TestChildList(props) {
  const getByStatus = result => {
    if (result.status === success) {
      return <PassedTest result={result}></PassedTest>;
    } else if (result.status === failed) {
      return <FailedTest result={result}></FailedTest>;
    } else {
      return <SkippedTest result={result}></SkippedTest>;
    }
  };
  return <div className="child-area">{getByStatus(props.result)}</div>;
}

export default TestChildList;
