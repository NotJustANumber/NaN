import React, { lazy, Suspense } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Header from "./components/Header";

const Welcome = lazy(() => import("./pages/welcome/Welcome"));
const ResultDetails = lazy(() => import("./pages/details/Results"));

function App() {
  return (
    <div className="App">
      <Router basename="/app">
        <Header />
        <Suspense fallback={<div>Loading...</div>}>
          <Switch>
            <Route exact path="/" component={Welcome}></Route>
            <Route path="/:id" component={ResultDetails} />
          </Switch>
        </Suspense>
      </Router>
    </div>
  );
}

export default App;
