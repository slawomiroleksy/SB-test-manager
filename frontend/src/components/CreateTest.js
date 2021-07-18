import React from 'react';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import Input from '@material-ui/core/Input';
import Redirect from 'react-router-dom/Redirect';
import Box from '@material-ui/core/Box';

export default function CreateTest({ test, onSuccess }) {
  const [name, setName] = React.useState('');
  const [success, setSuccess] = React.useState(false);

  const handleChange = (event) => {
    setName(event.target.value);
  };

  const handleSubmit = (event) => {
    createTest(name, 'UNDEFINED').then(() => setSuccess(true));
    event.preventDefault();
  };

  const createTest = async (name, status) => {
    await fetch(`/tests`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ name, status }),
    });
  };

  if (success) {
    return <Redirect to="/" />;
  }

  return (
    <form autoComplete="off" onSubmit={handleSubmit}>
      <FormControl>
        <InputLabel required htmlFor="component-simple">
          Name
        </InputLabel>
        <Input id="component-simple" value={name} onChange={handleChange} />
      </FormControl>
      <Box mt={3}>
        <input
          type="submit"
          value="Submit"
          {...(!name && { disabled: 'disabled' })}
        />
      </Box>
    </form>
  );
}
