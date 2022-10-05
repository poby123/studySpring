import { useState } from "react";
import { useEffect } from "react";

const test = {name: '클릭전!'};


export const Test = () => {

    const [testData, setTestData] = useState(test);

    return (
        <div>
            <p>{testData.name}</p>
            <button onClick={() => {setTestData({...testData, name: 'clicked!'}); console.log('클릭!')}}>클릭!</button>
        </div>
    )
}