import React, { useState } from 'react';
import axios from 'axios';

const ZoomMeeting: React.FC = () => {
    const [topic, setTopic] = useState('');
    const [startTime, setStartTime] = useState('');
    const [duration, setDuration] = useState('');
    const [meetingUrl, setMeetingUrl] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/api/zoom/create-meeting', null, {
                params: {
                    topic,
                    startTime,
                    duration
                }
            });
            setMeetingUrl(response.data);
        } catch (error) {
            console.error('Error creating meeting:', error);
        }
    };

    return (
        <div>
            <h2>Create Zoom Meeting</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Topic:</label>
                    <input type="text" value={topic} onChange={(e) => setTopic(e.target.value)} />
                </div>
                <div>
                    <label>Start Time:</label>
                    <input type="text" value={startTime} onChange={(e) => setStartTime(e.target.value)} />
                </div>
                <div>
                    <label>Duration:</label>
                    <input type="number" value={duration} onChange={(e) => setDuration(e.target.value)} />
                </div>
                <button type="submit">Create Meeting</button>
            </form>
            {meetingUrl && (
                <div>
                    <h3>Meeting URL:</h3>
                    <a href={meetingUrl} target="_blank" rel="noopener noreferrer">{meetingUrl}</a>
                </div>
            )}
        </div>
    );
};

export default ZoomMeeting;
