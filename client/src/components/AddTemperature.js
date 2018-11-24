import React, {Component} from 'react';

class AddTemperature extends Component {

    state = {lat: 0, lon: 0, temperature: 0, scale: ''};

    onFormSubmit = event => {
        event.preventDefault();
        this.props.onSubmit({...this.state});
    };

    render() {
        return (
            <div className="ui segment">
                <form onSubmit={this.onFormSubmit} className="ui form">
                    <div className="four fields">
                        <div className="field">
                            <label>Latitude</label>
                            <input type="text"
                                   value={this.state.lat}
                                   onChange={e => this.setState({lat: e.target.value})}/>
                        </div>
                        <div className="field">
                            <label>Longitude</label>
                            <input type="text"
                                   value={this.state.lon}
                                   onChange={e => this.setState({lon: e.target.value})}/>
                        </div>
                        <div className="field">
                            <label>Temperature</label>
                            <input type="text"
                                   value={this.state.temperature}
                                   onChange={e => this.setState({temperature: e.target.value})}/>
                        </div>
                        <div className="field">
                            <label>Scale</label>
                            <select value={this.state.scale}
                                    onChange={e => this.setState({scale: e.target.value})}
                                    className="ui fluid search dropdown">
                                <option value="">Default</option>
                                <option value="F">F</option>
                                <option value="K">K</option>
                                <option value="C">C</option>
                            </select>
                        </div>
                    </div>
                </form>
                <div>
                    <button onClick={this.onFormSubmit} className="ui submit button">Add</button>
                </div>
            </div>
        );
    }
}

export default AddTemperature;
