import React from 'react';

const Table = props => {
    return (
        <table className="ui celled table">
            <thead>
            <tr>{props.head.map(name => <th key={name}>{name}</th>)}</tr>
            </thead>
            <tbody>{props.data.map((row, i) =>
                <tr key={i}>
                    {row.map((val, j) => <td key={j}>{val}</td>)}
                </tr>
            )}</tbody>
        </table>
    );
};

export default Table;
