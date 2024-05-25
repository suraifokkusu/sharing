import jwt from 'jsonwebtoken';

const apiKey = 'UIHIogMdQdWoxyrjGtfwlw';
const apiSecret = '15SLtpc6gCtUlhSkIjhhXjHjVw5oQET7';

const payload = {
  iss: apiKey,
  exp: ((new Date()).getTime() + 5000)
};

const token = jwt.sign(payload, apiSecret);
console.log(token);
