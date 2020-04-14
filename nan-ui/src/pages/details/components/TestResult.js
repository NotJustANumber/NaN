import React from "react";
import TestParent from "./TestParent";
import TestChildList from "./TestChildList";

function TestResult(props) {
  return (
    <div style={props.style}>
      {props?.result?.container ? (
        <TestParent
          selectKey={props.selectKey}
          current={props.current}
          result={props.result}
        >
          {/* {props?.result?.children.map((s, index) => (
            <TestResult
              selectKey={props.selectKey}
              current={index}
              result={s}
              key={s.name}
            ></TestResult>
          ))} */}
        </TestParent>
      ) : (
        <TestChildList current={props.current} result={props.result}></TestChildList>
      )}
    </div>
  );
}

export default TestResult;
