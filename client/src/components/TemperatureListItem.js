import React, {Component} from 'react';

class TemperatureListItem extends Component {

    render() {
        const temperature = this.props.temperature;

        return (
            <tr>
                <td>{temperature.temperature}</td>
                <td>{temperature.lat}</td>
                <td>{temperature.lon}</td>
                <td>{temperature.time}</td>
            </tr>
        );
    }
}

export default TemperatureListItem;
