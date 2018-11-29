import React from 'react';

import AddTemperatureComponent from '../presentation/AddTemperatureComponent';

const AddTemperature = props => {
    return (<AddTemperatureComponent onSubmit={props.onSubmit}
                                     scaleOptions={['C', 'F', 'K']}/>);
};

export default AddTemperature;
