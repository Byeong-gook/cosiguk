import PropTypes from 'prop-types'
import styled from 'styled-components'

const CityStatsContainer = styled.div`
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    width: 445px;
    margin-top: 30px;
    align-items: center;
    justify-content: center;
`;
const CityStatsBlock = styled.div`
    display: flex;
    flex-direction: column;
    width: 190px;
    padding: 5px 5px 5px 5px;
    margin: 6px 6px 6px 6px;
    box-shadow: 0 1px 6px rgb(32 33 36 / 28%);
    border: 1px solid rgba(70, 77, 82, 0.082);
    border-radius: 13px;
    background-color: white;
`;
const CityTitle = styled.div`
    font-size: 16px;
    text-align: center;
`;
const CityInfoContainer = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding-left: 20px;
    padding-right: 20px;
    margin-top:1px;
`;
const CityInfoIsoContainer = styled(CityInfoContainer)`
    box-shadow: 0 1px 2px rgb(32 33 36 / 5%);
    border: 0px;
    border-radius: 20px;
    padding: 3px 15px 3px 15px;
    margin: 2px 10px 5px 10px; 
    background-color: #f1f1f1;
`;
const CityInfoIsoTitle = styled.div`
    font-size:15px;
`;
const CityInfoTitle = styled.div`
    font-size:14px;
`;
const StatsNum = styled.div`
    color: ${(props)=> props.textColor};
    font-size: 14px;
    font-weight: 500;
     
`;
function InfectionByCity ({cities}){
    let todayCityStats =  cities.slice(0,18);
    todayCityStats.sort((a, b) => {
        if(a.defCnt >= b.defCnt) return -1;
        else return 1;
    })
    return(
        <div>
            <CityStatsContainer>
                {todayCityStats.map((city,i) => 
                    <CityStatsBlock key={i}>
                        <CityTitle>{city.gubun}</CityTitle>
                        <CityInfoIsoContainer>
                            <CityInfoIsoTitle>격리중</CityInfoIsoTitle>
                            <StatsNum>{city.isolIngCnt.toLocaleString()}</StatsNum>
                        </CityInfoIsoContainer>
                        <CityInfoContainer>
                            <CityInfoTitle>확진환자</CityInfoTitle>
                            <StatsNum textColor="#5050ff">{city.defCnt.toLocaleString()}</StatsNum>
                        </CityInfoContainer>
                        <CityInfoContainer>
                            <CityInfoTitle>전일대비</CityInfoTitle>
                            <StatsNum textColor="#ff6262">
                                {city.incDec > 0 ?
                                "+" + city.incDec.toLocaleString()
                                :
                                city.incDec.toLocaleString()
                                }
                            </StatsNum>
                        </CityInfoContainer>
                        <CityInfoContainer>
                            <CityInfoTitle>격리해제</CityInfoTitle>
                            <StatsNum textColor="#5050ff">{city.isolClearCnt.toLocaleString()}</StatsNum>
                        </CityInfoContainer>
                        <CityInfoContainer>
                            <CityInfoTitle>사망자</CityInfoTitle>
                            <StatsNum textColor="#ff6262">{city.deathCnt.toLocaleString()}</StatsNum>
                        </CityInfoContainer>
                        </CityStatsBlock>
                )}
            </CityStatsContainer>
        </div>
    )
}
InfectionByCity.propTypes = {
    cities: PropTypes.array.isRequired,
}
export default InfectionByCity;