import React, { Component } from 'react';
import BrowserRouter from 'react-router-dom/BrowserRouter';
import Route from 'react-router-dom/Route';
import TestList from './components/TestList';
import CreateTest from './components/CreateTest';
import Container from '@material-ui/core/Container';
import { Link } from 'react-router-dom';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';

class App extends Component {
  render() {
    return (
      <Container maxWidth="sm">
        <Box p={10}>
          <BrowserRouter>
            <div>
              <Box mb={4}>
                <Button variant="contained" component={Link} to="/create">
                  Create a new test
                </Button>
              </Box>
              <Route exact path="/" component={TestList} />
              <Route exact path="/edit/:id" component={TestList} />
              <Route exact path="/create" component={CreateTest} />
              <Route exact path="/delete/:id" component={TestList} />
            </div>
          </BrowserRouter>
        </Box>
      </Container>
    );
  }
}

export default App;
