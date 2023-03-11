//SPDX-License-Identifier: Unlicense
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract CryptoCatapult is ERC20 {
    uint constant _initial_supply = 100000000 * (10**18);
    constructor() ERC20("CryptoCatapult", "CCP") {
        _mint(msg.sender, _initial_supply);
    }

    function giveMeTen() public {
        _mint(msg.sender, 10 * 10 ** decimals());
    }

    function giveTenTo(address ad) public {
        _mint(ad, 10 * 10 ** decimals());
    }

    function giveAmount(uint amount) public {
        _mint(msg.sender, amount * 10 ** decimals());
    }
}