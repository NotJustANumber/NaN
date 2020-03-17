import React, { useState, useEffect } from 'react';
import ResultCard from './components/ResultCard';
import TotalReport from './components/HeaderData';
import grid from '../../styles/grid.svg'
import axios from 'axios'

function Welcome() {
    const style = {
        right: "3rem"
    };


    const [projects, setProjects] = useState([]);

    useEffect(() => {
        axios.get("/v1/junit/report/").then(s => setProjects(s.data));
    }, []);

    // 
    return (
        <div className="WelcomePage">


            <img alt="decorative" className="top-0 absolute w-40 h-40" src={grid} style={style}></img>

            <div className="container mx-auto my-4">


                <TotalReport></TotalReport>

                <div className="flex justify-between mx-3 mt-8 pb-5 border-b border-gray-700">
                    <span className="text-2xl text-gray-600">Projects</span>
                    <button className="text-indigo-600" type="button">Refresh</button>
                </div>
                <div className="available-reports grid grid-cols-24 gap-2">
                    {projects.map(s => <ResultCard key={s.id} project={s}></ResultCard>)}
                </div>
            </div>
        </div>
    )
        ;
}

export default Welcome;
