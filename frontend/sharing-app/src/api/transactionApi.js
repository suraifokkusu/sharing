export async function fetchTransactions() {
    const response = await fetch('/api/transactions');
    if (!response.ok) {
      throw new Error('Failed to fetch transactions');
    }
    return await response.json();
  }
  
  export async function createTransaction(data) {
    const response = await fetch('/api/transactions', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    });
    if (!response.ok) {
      throw new Error('Failed to create transaction');
    }
    return await response.json();
  }
  