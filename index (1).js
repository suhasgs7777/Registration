const express = require('express');
const bodyParser = require('body-parser');
const multer = require('multer');
const fs = require('fs');
const pool = require('./db');


const app = express();
const PORT = process.env.PORT || 3000;

app.use(bodyParser.json());

const upload = multer({ dest: 'uploads/' });

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
app.post('/register', upload.single('photo'), async (req, res) => {
  try {
    const { name,mobileno,branch,location, department,designation} = req.body;
    const photo = req.file ? fs.readFileSync(req.file.path) : null;
    const query = 'INSERT INTO users (name,mobileno,branch,location, department,designation, photo) VALUES ($1, $2, $3, $4,$5,$6,$7) RETURNING id';
    const { rows } = await pool.query(query, [name,mobileno,branch,location, department,designation, photo]);
    res.status(201).json({ message: 'User registered successfully' });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});