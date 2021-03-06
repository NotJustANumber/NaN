import React from "react";
import {
  FailIcon,
  PassIcon,
  SkippedIcon,
  TotalTestsIcon,
} from "../../../components/Icons";

function TestParent(props) {
  const result = props.result;

  function getFailedCount(result) {
    return (
      <React.Fragment>
        <FailIcon className="mr-2"></FailIcon>
        {result}
      </React.Fragment>
    );
  }

  function getSkippedCount(result) {
    return (
      <React.Fragment>
        <SkippedIcon className="mr-2"></SkippedIcon>
        {result}
      </React.Fragment>
    );
  }

  function setVisible(e) {
    e.preventDefault();
    props.selectKey(props.current);
  }

  return (
    <div
      className={
        "test-parent-row px-2  hover:border-indigo-500  border " +
        (props.current % 2 === 0 ? "row-even" : "bg-gray-100")
      }
      onClick={setVisible}
    >
      <div className="individual-section cursor-pointer my-3 py-2  ">
        <div className="data-test flex  justify-center items-center">
          {/* {isShown ? (
            <Expanded className="text-gray-600 text-sm" />
          ) : (
            <Collapsed className="text-gray-600 text-sm"></Collapsed>
          )} */}

          <span className="text-md text-indigo-600 px-2 w-7/12 truncate">
            {result.name}
          </span>
          <div className="test-count-section flex w-5/12 mx-2 text-gray-600">
            <div className="total-test w-3/12 flex items-center">
              <TotalTestsIcon className="mr-2"></TotalTestsIcon>
              {result.passed + result.failed + result.skipped}
            </div>
            <div className="total-passed w-3/12 flex items-center">
              <PassIcon className="mr-2" />
              {result.passed}
            </div>
            <div className="total-failed w-2/12 flex items-center">
              {result.failed == 0 ? "-" : getFailedCount(result.failed)}
            </div>
            <div className="total-skipped w-2/12 flex items-center">
              {result.skipped == 0 ? "-" : getSkippedCount(result.skipped)}
            </div>
            <div className="duration w-2/12 flex items-center">
              {/* <DurationIcon className="mr-2"></DurationIcon> */}
              {result.duration / 1000}s
            </div>
          </div>
        </div>
      </div>
      {/* {isShown ? props.children : null} */}
    </div>
  );
}

export default TestParent;
