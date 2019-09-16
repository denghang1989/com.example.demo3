import React, { Component } from 'react';
import { Text, AppRegistry ,View,ToolbarAndroid} from 'react-native';
import {Container} from 'native-base'

export default class App extends Component {
  render() {
      return (
            <Container>
                <ToolbarAndroid/>
            </Container>
      );
  }}


AppRegistry.registerComponent('App', () => App);