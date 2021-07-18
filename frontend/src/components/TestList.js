import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';
import React, { useEffect } from 'react';
import Dropdown from './Dropdown';

export default function TestList() {
  const [tests, setTests] = React.useState('');

  useEffect(() => {
    if (!tests) {
      getTests();
    }
  }, [tests]);

  const getTests = async () => {
    const response = await fetch('/tests');
    const body = await response.json();
    setTests(body);
  };

  return (
    <div>
      {tests &&
        tests.map((test) => (
          <TableRow key={test.id}>
            <TableCell>{test.name}</TableCell>
            <TableCell>
              <Dropdown test={test} onSuccess={getTests} />
            </TableCell>
          </TableRow>
        ))}
    </div>
  );
}
