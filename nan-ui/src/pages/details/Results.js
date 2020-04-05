import React, { useState, useEffect } from "react";
import IndividualSummary from "./components/IndividualSummary";
import TestGroup from "./components/TestsGroup";
import Axios from "axios";
import { useParams } from "react-router-dom";
import { FailIcon, PassIcon } from "../../components/Icons";

function ResultDetails() {
  let { id } = useParams();
  const [results, setResults] = useState([]);

  useEffect(() => {
    Axios.get("/v1/junit/report/details/" + id).then((s) => setResults(s.data));
  }, []);

  const summary = results?.summary;

  return (
    <div className="Report-Summary tracking-tight">
      <div className="container mx-auto my-10">
        <div className="header-area flex items-center">
          <div className="test-name w-4/12">
            <div className="text-indigo-600 font-medium text-2xl capitalize">
              {summary?.name}
            </div>
            <IndividualSummary summary={summary}></IndividualSummary>
          </div>
          <div className="success-rate w-4/12">
            <div className="text-xl  text-teal-600">
              98% <span className="text-base">Success rate</span>
            </div>
          </div>

          <div className="histrory w-4/12">
            <div className="text-xl text-gray-600">History</div>
            <div className="old-results flex items-center mt-1 text-xl">
              <FailIcon></FailIcon>
              <span className="text-gray-600 mx-1">--</span>
              <PassIcon></PassIcon>
              <span className="text-gray-600 mx-1">--</span>
              <PassIcon></PassIcon>
              <span className="text-gray-600 mx-1">--</span>
              <FailIcon></FailIcon>
              <span className="text-gray-600 mx-1">--</span>
              <PassIcon></PassIcon>
              <span className="text-gray-600 mx-1">--</span>
              <PassIcon></PassIcon>
              <span className="text-gray-600 mx-1">--</span>
              <PassIcon></PassIcon>
            </div>
          </div>
        </div>

        <TestGroup results={results}></TestGroup>
      </div>
    </div>
  );
}

export default ResultDetails;
