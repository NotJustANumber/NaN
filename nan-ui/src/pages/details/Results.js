import Axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { FailIcon, PassIcon } from "../../components/Icons";
import IndividualSummary from "./components/IndividualSummary";
import TestGroup from "./components/TestsGroup";

function getStatusIcon(s) {
  return s === true ? (
    <React.Fragment>
      <PassIcon></PassIcon>
      <span className="text-gray-600 mx-1">--</span>
    </React.Fragment>
  ) : (
    <React.Fragment>
      <FailIcon></FailIcon>
      <span className="text-gray-600 mx-1">--</span>
    </React.Fragment>
  );
}

function ResultDetails() {
  let { id } = useParams();
  const [results, setResults] = useState([]);
  const [history, setHistory] = useState([]);

  useEffect(() => {
    Axios.get("/v1/test/report/details/" + id).then((s) => setResults(s.data));
    Axios.get("/v1/test/report/details/" + id + "/history").then((r) =>
      setHistory(r.data)
    );
  }, []);

  const summary = results?.summary;

  return (
    <div className="Report-Summary tracking-tight">
      <div className="container mx-auto my-8">
        <div className="header-area flex items-center">
          <div className="test-name w-4/12">
            <div className="text-indigo-600 font-medium text-2xl capitalize">
              {summary?.name}
            </div>
            <IndividualSummary summary={summary}></IndividualSummary>
          </div>
          <div className="success-rate w-4/12 flex justify-center">
            <div className="text-2xl  text-teal-600">
              {Math.round((summary?.passed / summary?.total) * 10000) / 100 +
                " % "}
              <span className="text-base text-gray-600">Test success rate</span>
            </div>
          </div>

          <div className="histrory w-4/12">
            <div className="text-xl text-gray-600">History</div>
            <div className="old-results flex items-center mt-1 text-xl">
              {history ? (
                history?.map((s) => getStatusIcon(s))
              ) : (
                <span>No history found</span>
              )}
              {/* <FailIcon></FailIcon>
              
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
              <PassIcon></PassIcon> */}
            </div>
          </div>
        </div>

        <TestGroup results={results}></TestGroup>
      </div>
    </div>
  );
}

export default ResultDetails;
