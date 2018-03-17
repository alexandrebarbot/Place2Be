import React from 'react';
import Header from './header/Header';
import Main from './main/Main';

export default class App extends React.Component {
    render() {
        return (
            <React.Fragment>
                <Header />
                <Main />
            </React.Fragment>
        );
    }
}