// jest.config.js
export default {
    extensionsToTreatAsEsm: ['.js'],
    globals: {
      'ts-jest': {
        useESM: true,
      },
    },
    testEnvironment: 'node', // Set your desired test environment
  };
  