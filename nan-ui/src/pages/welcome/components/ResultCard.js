import React from "react";
import { useHistory } from "react-router-dom";

function ResultCard(props) {
  let history = useHistory();
  return (
    <div
      className={
        (props.project.total > 12000 ? "col-span-7" : "col-span-6") +
        " rounded hover:border-indigo-600 border border-gray-500 mx-3 mt-5 overflow-hidden cursor-pointer shadow-lg hover:shadow-2xl"
      }
      onClick={() => history.push("/" + props.project.id)}
    >
      <div className="card-header bg-gray-100 px-6 py-3">
        <div className="text-indigo-600 capitalize text-lg font-medium report-name">
          {props.project.name.split(":")[0]}
        </div>
        <div className="text-indigo-600 text-xs my-1">
          {props.project.name.split(":")[1]}
        </div>
        <div className="report-secondary-data mt-2 flex">
          <div className="report-total-test text-xs text-gray-600  mr-3">
            {props.project.total} tests
          </div>
          <div className="text-xs text-gray-600 ">
            {props.project.skipped} Skipped
          </div>
        </div>
      </div>
      <div className="result-area px-6 py-5">
        {/* <div className="text-indigo-600 text-lg font-medium report-name">TEST SUITE NAME</div>
            <div className="report-secondary-data mt-2 flex">
                <div className="report-total-test text-sm text-gray-600  mr-3">17 tests</div>
                <div className="text-xs text-gray-600 ">2 Skipped</div>
            </div> */}

        <div className="report-result-area flex justify-between  mb-4">
          <div className="passed-count flex  items-center text-gray-700 text-4xl">
            {props.project.passed}

            <div className="w-5 rounded-md ml-2 h-1 bg-teal-500"></div>
          </div>
          <div className="border-r mx-3 border-gray-400"></div>
          <div className="failed-count flex   items-center text-gray-700 text-4xl mr-8">
            {props.project.failed}
            <div className="w-5 rounded-md ml-2 h-1 bg-red-500"></div>
          </div>
        </div>

        <div className="report-last mt-3 flex justify-between">
          <div className="text-xs text-gray-600 ">{props.project.modified}</div>
        </div>
      </div>
    </div>
  );
}
export default ResultCard;
