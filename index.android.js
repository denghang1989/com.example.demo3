import React, { Component } from 'react';
import { Text, AppRegistry ,View} from 'react-native';
import {Button,Container} from 'native-base'

export default class App extends Component {
  render() {
      return (
              <Container>
                  <Text>Hello world</Text>
                  <Button light><Text>button</Text></Button>
              </Container>
      );
  }}


AppRegistry.registerComponent('App', () => App);