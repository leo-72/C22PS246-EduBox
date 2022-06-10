'use strict';

module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('books', {
      id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNul: false,
      },
      name: {
        type: Sequelize.STRING,
        allowNul: false,
      },
      author: {
        type: Sequelize.STRING,
        allowNul: false,
      },
      description: {
        type: Sequelize.TEXT,
      },
      createdAt: {
        type: Sequelize.DATE,
        allowNul: false
      },
      updatedAt: {
        type: Sequelize.DATE,
        allowNul: false
      },
    });

  },

  async down(queryInterface, Sequelize) {
    await queryInterface.dropTable('books');

  }
};
