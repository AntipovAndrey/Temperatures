import React from 'react';

const Table = props => {
    return (
        <table className="ui celled table">
            <thead>
            <tr>{props.head.map(name => <th>{name}</th>)}</tr>
            </thead>
            <tbody>{props.data.map(row => <tr>{row.map(val => <td>{val}</td>)}</tr>)}</tbody>
        </table>
    );
};

export default Table;
