var express = require('express');
var router = express.Router();
const Validator = require('fastest-validator');

const { Book } = require('../models');
const v = new Validator();

router.get('/', async (req, res) => {
    const books = await Book.findAll();
    return res.json(books);
});
router.get('/:id', async (req, res) => {
    const id = req.params.id;
    const book = await Book.findByPk(id);
    return res.json(book || {});
});
router.post('/', async (req, res) =>{
    const schema = {
        name: 'string',
        author: 'string|optional',
        description: 'string|optional'
    }
    const validate = v.validate(req.body, schema);
    if (validate.length){
        return res
            .status(400)
            .json(validate);
    }
    const book = await Book.create(req.body);

    res.json(book);
});
router.put('/:id', async (req, res) => {
    const id = req.params.id;
    let book = await Book.findByPk(id);
    if (!book){
        return res.json({message: 'book tidak di temukan'});
    }
    const schema = {
        name: 'string|optional',
        author: 'string|optional',
        description: 'string|optional'
    }
    const validate = v.validate(req.body, schema);
    if (validate.length) {
        return res
            .status(400)
            .json(validate);
    }
    book = await book.update(req.body);
    res.json(book);
});

router.delete('/:id', async(req, res) =>{
    const id = req.params.id;
    // res.send(id); req.params.id;
    // res.send(id);

    const book = await Book.findByPk(id);
    if(!book) {
        return res.json({ message: 'book tidak di temukan' });
    }
    await book.destroy();

    res.json({
        message: 'book Terdelete'
    });
});
module.exports = router;