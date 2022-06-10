// const { sequelize, DataTypes } = require("sequelize/types");

module.exports = (sequelize, DataTypes) => {
    const Book = sequelize.define('Book',{
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
            allowNul: false,
        },
        name: {
            type: DataTypes.STRING,
            allowNul: false,
        },
        author: {
            type: DataTypes.STRING,
            allowNul: false,
        },
        description: {
            type: DataTypes.TEXT,
        },
        createdAt: {
            type: DataTypes.DATE,
            allowNul: false
        },
        updatedAt: {
            type: DataTypes.DATE,
            allowNul: false
        },
    },
    {
        tableName: "books"
    });

    return Book;
}