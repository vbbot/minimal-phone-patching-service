# Instructions:

**(make sure your bootloader is unlocked! and you have disabled vbmeta verification by reflashing with `fastboot flash vbmeta --disable-verity --disable-verification vbmeta.img`)**

- reboot to fastbootd using `adb reboot fastboot` (or any other method)
- run `fastboot erase product`
- run `fastboot resize-logical-partition product 0`
- run `fastboot flash system system_patched.img`
- after that's done, run `fastboot -w`
- run `fastboot reboot` and give it a few minutes

Default E-ink features Usage:

**Single Press E-Ink Button** - Refresh Screen

**Double Press E-Ink Button** - Open E-Ink Menu with settings for Per-App refresh modes.

These mappings can be easily changed in the E-Ink Settings app

Also, the default refresh mode can be changed in the e-ink settings app. The ideal mode is balanced. The other modes might make some elements invisible.

## Keyboard
To make sure the keyboard works correctly, download the FinQwerty build from [here](https://github.com/vbbot/finqwerty/releases/tag/mp01-20250629) and follow the instructions mentioned in the release.

# Licensing

## MIT License
Most of the project is licensed under the MIT License unless specified otherwise

The code and releases are provided “as is,” without any express or implied warranty of any kind including warranties of merchantability, non-infringement, title, or fitness for a particular purpose.

## Apache License 2.0
The file `HardwareGestureDetector.kt` includes code derived from AOSP. The original code is subject to the following license:

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

Modifications and additions to the original code are licensed under the MIT License
