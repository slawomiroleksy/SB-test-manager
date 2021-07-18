import React from 'react';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';

export default function Dropdown({ test, onSuccess }) {
  const handleChange = (event) => {
    updateTest(test.id, test.name, event.target.value).then(onSuccess);
  };

  const updateTest = async (id, name, status) => {
    await fetch(`/tests/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ name, status }),
    });
  };

  return (
    <FormControl>
      <Select value={test.status} onChange={handleChange}>
        <MenuItem value="UNDEFINED">Undefined</MenuItem>
        <MenuItem value="PASSED">Passed</MenuItem>
        <MenuItem value="FAILED">Failed</MenuItem>
      </Select>
    </FormControl>
  );
}
