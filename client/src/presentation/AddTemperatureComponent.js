import React, {useState} from 'react';

const AddTemperatureComponent = props => {

    const [lat, setLat] = useState(0);
    const [lon, setLon] = useState(0);
    const [scale, setScale] = useState('');
    const [temperature, setTemperature] = useState(0);

    const onFormSubmit = event => {
        event.preventDefault();
        props.onSubmit({lat, lon, temperature, scale});
    };

    return (
        <div className="ui segment">
            <form className="ui form">
                <div className="four fields">
                    <div className="field">
                        <label>Latitude</label>
                        <input type="text" value={lat}
                               onChange={e => setLat(e.target.value)}/>
                    </div>
                    <div className="field">
                        <label>Longitude</label>
                        <input type="text" value={lon}
                               onChange={e => setLon(e.target.value)}/>
                    </div>
                    <div className="field">
                        <label>Temperature</label>
                        <input type="text" value={temperature}
                               onChange={e => setTemperature(e.target.value)}/>
                    </div>
                    <div className="field">
                        <label>Scale</label>
                        <select value={scale} onChange={e => setScale(e.target.value)}
                                className="ui fluid search dropdown">
                            {props.scaleOptions.map((val, i) => <option key={i}>{val}</option>)}
                        </select>
                    </div>
                </div>
            </form>
            <div>
                <button onClick={onFormSubmit}
                        className="ui submit button">
                    Add
                </button>
            </div>
        </div>
    );
};

export default AddTemperatureComponent;
