import React, {Component} from 'react';

import TemperatureRow from './TemperatureListItem';

class TemperatureTable extends Component {

    getTemperatures = () => {
        return this.props.temperatures.map(temperature => {
            return <TemperatureRow key={temperature.time} temperature={temperature}/>
        });
    };

    render() {
        return (
            <table className="ui celled table">
                <thead>
                <tr>
                    <th>Value, C</th>
                    <th>Lat</th>
                    <th>Lon</th>
                    <th>Timestamp</th>
                </tr>
                </thead>
                <tbody>
                {this.getTemperatures()}
                </tbody>
            </table>
        );
    }
}

export default TemperatureTable;
