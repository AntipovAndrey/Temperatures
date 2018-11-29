import React from 'react';

import Table from '../presentation/Table';

const TemperatureTable = ({temperatures}) => {
    return (<Table head={['Value, C', 'Lat', 'Lon', 'Timestamp']}
                   data={temperatures.map(({temperature, lat, lon, time}) => [temperature, lat, lon, time])}/>);
};

export default TemperatureTable;
