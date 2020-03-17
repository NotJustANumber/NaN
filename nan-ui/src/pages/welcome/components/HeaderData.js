import React, { useState, useEffect } from 'react'
import axios from 'axios'


function TotalReport() {


    const [total, setTotal] = useState([]);

    useEffect(() => {
        axios.get("/v1/junit/report/total").then(s => setTotal(s.data));
    }, []);

    return (
        <div className="w-10/12 my-10">
            <div className="flex items-center  justify-between ml-3 mt-4">
                <div className="flex flex-col mr-8">
                    <span className="total-projects text-gray-600 text-base ">Projects</span>
                    <span className="count text-gray-700 text-xl">{total[0]}</span>
                </div>

                <div className="flex flex-col mx-8">
                    <span className="total-projects text-gray-600 text-base ">Total Tests</span>
                    <span className="count text-gray-700 text-xl">{total[1]}</span>
                </div>

                <div className="flex flex-col mx-8">
                    <span className="total-projects text-gray-600 text-base ">Total Passes</span>
                    <span className="count text-gray-700 text-xl">{total[2]}</span>
                </div>

                <div className="flex flex-col ml-8">
                    <span className="total-projects text-gray-600 text-base ">Total failure</span>
                    <span className="count text-gray-700 text-xl">{total[3]}</span>
                </div>

                <div className="flex flex-col ml-8">
                    <span className="total-projects text-gray-600 text-base ">Total skipped</span>
                    <span className="count text-gray-700 text-xl">{total[4]}</span>
                </div>


            </div>
            <div className="progress-bard h-4 mt-4 mx-3 rounded-md border border-gray-400">
                <div className="fill h-4 rounded-md bg-gray-600 w-8/12"></div>
            </div>
        </div>);
}

export default TotalReport;