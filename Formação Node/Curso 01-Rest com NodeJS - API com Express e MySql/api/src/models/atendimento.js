'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Atendimento extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
    }
  };
  Atendimento.init({
    cliente: DataTypes.STRING,
    pet: DataTypes.STRING,
    dataAgendamento: DataTypes.STRING
  }, {
    sequelize,
    modelName: 'Atendimento',
  });
  return Atendimento;
};