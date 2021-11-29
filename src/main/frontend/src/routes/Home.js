import axios from 'axios';
import styled from 'styled-components'
import UseAsync from "../hooks/UseAsync.js";
import InfectionStats from '../components/InfectionStats.js'
import InfectionByCity from '../components/InfectionByCity.js'

const HomeContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
`;
const HomeHeader = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    height: 80px;
`;
const HomeHeaderTitle = styled.div`
    font-size: 20px;
    font-weight: bold;
`;
const HomeHeaderFooter= styled.div`
    font-size: 12px;
`;
const InfectionStatsContainer  = styled.div`
`;
const InfectionByRegionContainer = styled.div`
`;

function Home(){
    async function getInfectionStatus(){
        const response = await axios.get("/api/data/infectionStatus");
        return response.data;
    }
    async function getInfectionCity(){
        const response = await axios.get("/api/data/infectionCity");
        return response.data;
    }
        //eslint-disable-next-line
    const [infStatState, statRefetch] = UseAsync(getInfectionStatus, []);
        //eslint-disable-next-line
    const [infCityState, cityRefetch] = UseAsync(getInfectionCity, []);
    const {loading : infStatLoading, data : infStatData, error : infStatError} = infStatState;
    const {loading : infCityLoading, data : infCityData, error : infCityError} = infCityState;

    if(infStatLoading || infCityLoading) return <div>Loading</div>;
    if(infStatError || infCityError) return <div>error page</div>;
    if(!infStatData || !infCityData) return <div>loading</div>
    const infectionStatusData = infStatData.response.body.items.item;
    const infectionCityData = infCityData.response.body.items.item;

    return(
        <HomeContainer>
            <HomeHeader>
                <HomeHeaderTitle>코로나19 상황판</HomeHeaderTitle>
                <HomeHeaderFooter>{(infectionStatusData[0].createDt).slice(0,16)} 기준</HomeHeaderFooter>
            </HomeHeader>
            <InfectionStatsContainer>
                <InfectionStats 
                todayCnt={infectionStatusData[0]}
                yesterdayCnt={infectionStatusData[1]}
                />
            </InfectionStatsContainer>
            <InfectionByRegionContainer>
                <InfectionByCity cities={infectionCityData}/>
            </InfectionByRegionContainer>
        </HomeContainer>
    )
}
export default Home;