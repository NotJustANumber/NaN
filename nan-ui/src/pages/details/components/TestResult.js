import React from "react";
import TestParent from "./TestParent";
import TestChildList from "./TestChildList";

function TestResult(props) {
  return (
    <React.Fragment>
      {props?.result?.container ? (
        <TestParent result={props.result}>
          {props?.result?.children.map(s => (
            <TestResult result={s} key={s.name}></TestResult>
          ))}
        </TestParent>
      ) : (
        <TestChildList result={props.result}></TestChildList>
      )}
    </React.Fragment>
  );
}

export default TestResult;
