import React from "react";
import TestResult from "./TestResult";
import { useState } from "react";
import { Back } from "../../../components/Icons";
import { FixedSizeList as List } from "react-window";

function TestGroup(props) {
  const fullResult = props?.results?.resultString;
  const [key, setKey] = useState("");
  const [result, setResult] = useState(fullResult);
  const [isFiltered, setFilter] = useState(false);
  const [tempResult, saveTemp] = useState([]);

  const filterFailedTest = () => {
    const filterSelection = !isFiltered;
    setFilter(filterSelection);
    if (filterSelection) {
      saveTemp([...result]);
      setResult(filterResult());
    } else {
      setResult(tempResult);
    }
  };

  const filterResult = () => {
    if (result) {
      const data = result
        .flatMap((s) => getRecursiveChildren(s))
        .filter((s) => s !== null)
        .filter((s) => s.status === "FAILED");
      return data;
    }
    return result;
  };

  const getRecursiveChildren = (r) => {
    console.log("Getting for ", r);
    if ((r && r.children && r.failed && r.failed > 0) || !r.container) {
      const childs = r.container
        ? r.children
            .flatMap((k) => getRecursiveChildren(k))
            .filter((s) => s !== null)
        : r;
      console.log(childs);
      return childs;
    }
    console.log("Returning null");
    return null;
  };

  React.useEffect(() => {
    setResult(fullResult);
  }, [fullResult]);

  const selectKey = (newKey) => {
    let keySelected = "";
    if (!key) {
      keySelected = newKey.toString();
    } else {
      keySelected = key + "." + newKey;
    }
    setKey(keySelected);
    updateResult(keySelected);
  };

  const updateResult = (keySelected) => {
    if (!keySelected) {
      setResult(fullResult);
      return;
    }
    const arraySelected = keySelected.split(".");
    let selected = fullResult[arraySelected[0]];
    for (let i = 1; i < arraySelected.length; i++) {
      selected = selected.children[arraySelected[i]];
    }
    setResult(selected.children);
    // console.log(selected);
  };

  const goBack = () => {
    if (key.length === 1) {
      setKey("");
      updateResult("");
      return;
    }
    const newKey = key.substr(0, key.lastIndexOf("."));
    console.log("The key selected : ", newKey, key);
    setKey(newKey);
    updateResult(newKey);
  };

  const TestArea = ({ index, style }) => {
    return (
      <TestResult
        style={style}
        selectKey={selectKey}
        result={result[index]}
        current={index}
      ></TestResult>
      // <div style={style}>Row {index}</div>
    );
  };

  return (
    <div className="mt-5">
      <div className="header  border-b border-gray-700 flex p-5 pb-3 text-gray-600 flex justify-between items-center">
        <div className="column-1 w-7/12  flex text-xl">
          Feature
          {result && result?.some((s) => s.failed != 0) ? (
            <label className="md:w-2/3 block text-gray-500">
              <input
                className="mr-2 mx-3 leading-tight"
                checked={isFiltered}
                onChange={filterFailedTest}
                type="checkbox"
              />
              <span className="text-sm">Show me failed tests</span>
            </label>
          ) : null}
        </div>
        <div className="count-header w-5/12 flex">
          <div className="w-3/12">Total</div>
          <div className="w-3/12">Passed</div>
          <div className="w-2/12">Failed</div>
          <div className="w-2/12">Skipped</div>
          <div className="w-2/12">Duration</div>
        </div>
      </div>

      {key ? (
        <button className="my-4" onClick={goBack}>
          <Back></Back>
        </button>
      ) : null}
      <div className="result-section mt-2  bg-white border shadow-lg rounded-md">
        {result?.length > 20 ? (
          <List
            className="List"
            height={900}
            itemCount={result?.length}
            itemSize={74}
          >
            {TestArea}
          </List>
        ) : (
          result?.map((s, index) => TestArea({ index }))
        )}
      </div>
    </div>
  );
}

export default TestGroup;
