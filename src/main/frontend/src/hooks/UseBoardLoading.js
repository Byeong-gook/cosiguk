import {useState} from "react";

export default function UseBoardLoading(initBool){
    const [isLoading, setLoading] = useState(initBool);
    return [isLoading, setLoading];
}