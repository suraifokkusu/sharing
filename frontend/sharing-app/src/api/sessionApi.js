export async function fetchSessions() {
    const response = await fetch('/api/sessions');
    if (!response.ok) {
      throw new Error('Failed to fetch sessions');
    }
    return await response.json();
  }
  