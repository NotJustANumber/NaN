import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  PassIcon,
  TotalTestsIcon,
  SkippedIcon,
  FailIcon
} from "../../../components/Icons";

function TotalReport() {
  const [total, setTotal] = useState([]);

  useEffect(() => {
    axios.get("/v1/test/report/total").then(s => setTotal(s.data));
  }, []);

  return (
    <div className="p-5">
      <div className="flex items-center justify-between ml-3">
        <div className="flex flex-col items-center">
          <span className="total-projects text-gray-600 text-base ">
            Projects
          </span>
          <span className="count flex items-center text-gray-700 mt-2 text-xl">
            {total[0]}
          </span>
        </div>

        <div className="flex flex-col items-center mx-8">
          <span className="total-projects text-gray-600 text-base ">
            Total Tests
          </span>
          <span className="count flex items-center text-gray-700 mt-2 text-xl">
            <TotalTestsIcon className="text-sm mr-1"></TotalTestsIcon>
            {total[1]}
          </span>
        </div>

        <div className="flex flex-col items-center mx-8">
          <span className="total-projects text-gray-600 text-base ">
            Total Passes
          </span>
          <span className="count flex items-center text-gray-700 mt-2 text-xl">
            <PassIcon className="text-sm mr-1"></PassIcon>
            {total[2]}
          </span>
        </div>

        <div className="flex flex-col items-center ml-8">
          <span className="total-projects text-gray-600 text-base ">
            Total failure
          </span>
          <span className="count flex items-center text-gray-700 mt-2 text-xl">
            <FailIcon className="text-sm mr-1"></FailIcon>
            {total[3]}
          </span>
        </div>

        <div className="flex flex-col items-center ml-8">
          <span className="total-projects text-gray-600 text-base ">
            Total skipped
          </span>
          <span className="count flex items-center text-gray-700 mt-2 text-xl">
            <SkippedIcon className="text-sm mr-1"></SkippedIcon>
            {total[4]}
          </span>
        </div>
      </div>
      {/* <div className="progress-bard h-4 mt-4 mx-3 rounded-md border border-gray-400">
                <div className="fill h-4 rounded-md bg-gray-600 w-8/12"></div>
            </div> */}
    </div>
  );
}

export default TotalReport;
