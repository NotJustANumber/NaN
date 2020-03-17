import React from "react";
import { PassIcon, DurationIcon } from "../../../components/Icons";

function PassedTest(props) {
  return (
    <div className="test-child px-8 text-gray-700 py-2 flex items-center mx-auto">
      <PassIcon className="mr-3"></PassIcon>
      <span className="w-9/12">{props.result.name}</span>
      <div className="duration-area ml-4  text-gray-500 flex items-center">
        <DurationIcon className="mr-3"></DurationIcon>
        {props.result.duration}
      </div>
    </div>
  );
}

export default PassedTest;
