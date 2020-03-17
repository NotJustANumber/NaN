import React from 'react';
import Welcome from "./pages/welcome/Welcome";
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import ResultDetails from './pages/details/Results';
import Header from './components/Header';

function App() {
    return (
        <div className="App">
            <Router>
                <Header />

                <Switch>
                    <Route exact path="/">
                        <Welcome></Welcome>
                    </Route>
                    <Route path="/:id" children={<ResultDetails />} />
                </Switch>

            </Router>

        </div>
    );
}

export default App;
