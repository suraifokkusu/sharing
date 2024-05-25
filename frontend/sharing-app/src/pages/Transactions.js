import React, { useState, useEffect } from 'react';
import './Transaction.css'; // Ссылка на файл стилей

function Transactions() {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    // Здесь должен быть вызов API для загрузки данных о транзакциях
    setTransactions([{ id: 1, amount: 100, sender: "Alice", receiver: "Bob" }]);
  }, []);

  return (
    <div className="transactions">
      <h1>Recent Transactions</h1>
      <ul>
        {transactions.map(transaction => (
          <li key={transaction.id}>{transaction.sender} sent ${transaction.amount} to {transaction.receiver}</li>
        ))}
      </ul>
    </div>
  );
}

export default Transactions;
